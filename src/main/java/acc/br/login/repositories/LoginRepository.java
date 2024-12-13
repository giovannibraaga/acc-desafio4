package acc.br.login.repositories;

import acc.br.login.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

    @Query("Select l from Login l where l.username = :username")
    Login findByUsername(@Param("username") String username);
}
