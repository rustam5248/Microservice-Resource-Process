package uz.epam.resourceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.epam.resourceservice.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
