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

    public SystemManager editSystemManager(SystemManagerDTO systemManagerDTO) {
        SystemManager systemManager = systemManagerRepository.findById(systemManagerDTO.getId());
        updateSystemMenager(systemManager, systemManagerDTO);

        try {
            systemManager = systemManagerRepository.save(systemManager);
            return systemManager;
        } catch (Exception e) {
            return null;
        }
    }

    private void updateSystemMenager(SystemManager systemManager, SystemManagerDTO systemManagerDTO) {
        systemManager.setName(systemManagerDTO.getName());
        systemManager.setSurname(systemManagerDTO.getSurname());
        systemManager.setBirthDate(systemManagerDTO.getBirthDate());
        systemManager.setPhoneNumber(systemManagerDTO.getPhoneNumber());
    }
}
