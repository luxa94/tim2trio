package rs.isa.mrs.trio.iceipice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.SystemManager;
import rs.isa.mrs.trio.iceipice.model.dto.SystemManagerDTO;
import rs.isa.mrs.trio.iceipice.repository.SystemManagerRepository;

/**
 * Created by Nina on 26-Apr-16.
 */
@Service
public class SystemManagerService {

    @Autowired
    SystemManagerRepository systemManagerRepository;

    @Autowired
    BaseUserService baseUserService;

    public SystemManager editSystemManager(SystemManagerDTO systemManagerDTO) {
        SystemManager systemManager = systemManagerRepository.findById(systemManagerDTO.getId());
        updateSystemManager(systemManager, systemManagerDTO);

        try {
            systemManager = systemManagerRepository.save(systemManager);
            return systemManager;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateSystemManager(SystemManager systemManager, SystemManagerDTO systemManagerDTO) {
        baseUserService.updateChangeable(systemManager, systemManagerDTO);
    }
}
