package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.model.Provider;
import rs.isa.mrs.trio.iceipice.model.dto.ProviderDTO;
import rs.isa.mrs.trio.iceipice.repository.ProviderRepository;

/**
 * Created by nikolalukic on 5/10/16.
 */
@Service
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    @Autowired
    BaseUserService baseUserService;

    public Provider editProvider(ProviderDTO providerDTO) {
        Provider p = providerRepository.findById(providerDTO.getId());
        updateProvider(p, providerDTO);

        try {
            p = providerRepository.save(p);
            return p;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateProvider(Provider p, ProviderDTO providerDTO) {
        baseUserService.updateChangeable(p, providerDTO);
    }


}
