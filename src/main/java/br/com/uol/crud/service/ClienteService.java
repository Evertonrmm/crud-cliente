package br.com.uol.crud.service;

import br.com.uol.crud.common.MessageConstants;
import br.com.uol.crud.controller.client.GeoLocalizacaoClientController;
import br.com.uol.crud.controller.client.MetaWeatherClientController;
import br.com.uol.crud.expections.BusinessErrorException;
import br.com.uol.crud.expections.NoContentException;
import br.com.uol.crud.model.Cliente;
import br.com.uol.crud.model.client.IpVigilante;
import br.com.uol.crud.model.client.MetaWeather;
import br.com.uol.crud.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Autowired
    ClienteService(ClienteRepository clienteRepository){
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvar(Cliente cliente, String ip){

        obterTemperatura(cliente, ip);

        return clienteRepository.save(cliente);
    }

    private void obterTemperatura(Cliente cliente, String ip){

        IpVigilante ipVigilante = obterLocalizacaoPorIp(ip);

        MetaWeatherClientController metaWeatherClientController = new MetaWeatherClientController();

        MetaWeather localizacao = obterLocalizacaoPorLatitudeELongitude(metaWeatherClientController, ipVigilante.getData().getLatitude(), ipVigilante.getData().getLongitude());

        MetaWeather clima = metaWeatherClientController.obterClima(localizacao.getWoeid());

        if(clima == null || CollectionUtils.isEmpty(clima.getConsolidatedWeather())){
            throw new BusinessErrorException("Não foi possível encontrar temperatura da localidade informada.");
        }

        cliente.setTemperaturaMinima(clima.getConsolidatedWeather().get(0).getMinTemp());
        cliente.setTemperaturaMaxima(clima.getConsolidatedWeather().get(0).getMaxTemp());
    }

    private IpVigilante obterLocalizacaoPorIp(String ip){
        GeoLocalizacaoClientController geoLocalizacaoClientController = new GeoLocalizacaoClientController();

        IpVigilante ipVigilante = geoLocalizacaoClientController.obterLocalizacao(ip);

        if(ipVigilante == null){
            throw new BusinessErrorException("Não foi possível obter localização do cliente.");
        }

        return ipVigilante;
    }

    private MetaWeather obterLocalizacaoPorLatitudeELongitude(MetaWeatherClientController controller, String latitude, String longitude){
        List<MetaWeather> localizacoes = controller.obterLocalizacao(latitude, longitude);

        if(CollectionUtils.isEmpty(localizacoes)){
            throw new BusinessErrorException("Não foi possível encontrar nenhuma localização próxima a latitude e longitude informadas.");
        }

        return localizacoes.get(0);
    }

    public void delete(Long id){

        Optional<Cliente> clientePersistido = clienteRepository.findById(id);

        if(!clientePersistido.isPresent()){
            throw new NoContentException(MessageConstants.OBJETO_NAO_ENCONTRADO);
        }

        clienteRepository.deleteById(id);
    }

    public List<Cliente> buscarTodos(){

        List<Cliente> clientes = clienteRepository.findAll();

        if(CollectionUtils.isEmpty(clientes)){
            throw new NoContentException(MessageConstants.OBJETO_NAO_ENCONTRADO);
        }

        return clientes;
    }

    @Transactional(readOnly = true)
    public Cliente buscarPorId(Long id){

        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(!cliente.isPresent()){
            throw new NoContentException(MessageConstants.OBJETO_NAO_ENCONTRADO);
        }

        return cliente.get();
    }

    public Cliente atualizar(Cliente cliente) {

        Optional<Cliente> clientePersistido = clienteRepository.findById(cliente.getId());

        if(!clientePersistido.isPresent()){
            throw new NoContentException(MessageConstants.OBJETO_NAO_ENCONTRADO);
        }

        clientePersistido.get().setIdade(cliente.getIdade());
        clientePersistido.get().setNome(cliente.getNome());

        return clienteRepository.save(clientePersistido.get());
    }
}
