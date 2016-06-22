package rs.isa.mrs.trio.iceipice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.isa.mrs.trio.iceipice.model.FriendshipRequest;
import rs.isa.mrs.trio.iceipice.model.Grade;

/**
 * Created by Nina on 22-Jun-16.
 */
public interface FriendshipRequestRepository extends JpaRepository<FriendshipRequest, Long> {

    FriendshipRequest findById(long id);
}
