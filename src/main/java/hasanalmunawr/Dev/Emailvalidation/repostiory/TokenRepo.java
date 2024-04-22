package hasanalmunawr.Dev.Emailvalidation.repostiory;

import hasanalmunawr.Dev.Emailvalidation.Entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepo extends CrudRepository<Token, Integer> {

    Optional<Token> findByToken(String token);
}
