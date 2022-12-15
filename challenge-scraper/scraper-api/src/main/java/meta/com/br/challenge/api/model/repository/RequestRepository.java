package meta.com.br.challenge.api.model.repository;

import meta.com.br.challenge.api.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query("SELECT r FROM Request r WHERE r.repositorio = ?1")
    Request findRequest(String name);

}
