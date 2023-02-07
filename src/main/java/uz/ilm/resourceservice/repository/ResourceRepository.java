package uz.ilm.resourceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.ilm.resourceservice.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
