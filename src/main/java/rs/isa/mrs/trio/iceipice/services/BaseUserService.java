package rs.isa.mrs.trio.iceipice.services;

import org.springframework.stereotype.Service;
import rs.isa.mrs.trio.iceipice.model.BaseUser;
import rs.isa.mrs.trio.iceipice.model.dto.BaseUserDTO;

/**
 * Created by nikolalukic on 5/15/16.
 */
@Service
public class BaseUserService {

    public void updateChangeable(BaseUser baseUser, BaseUserDTO baseUserDTO) {
        baseUser.setName(baseUserDTO.getName());
        baseUser.setSurname(baseUserDTO.getSurname());
        baseUser.setBirthDate(baseUserDTO.getBirthDate());
        baseUser.setPhoneNumber(baseUserDTO.getPhoneNumber());
    }

    public void extractData(BaseUser baseUser, BaseUserDTO baseUserDTO) {
        baseUser.setEmail(baseUserDTO.getEmail());
        baseUser.setPassword(baseUserDTO.getPassword());
        updateChangeable(baseUser, baseUserDTO);
    }

    public void extractDataWithId(BaseUser baseUser, BaseUserDTO baseUserDTO) {
        baseUser.setId(baseUserDTO.getId());
        extractData(baseUser, baseUserDTO);
    }

}
