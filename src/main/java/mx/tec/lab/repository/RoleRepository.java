package mx.tec.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.tec.lab.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
