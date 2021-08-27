package ru.savkin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.savkin.model.stocks.TestDocPerson;

@Repository
public interface TestRepository extends MongoRepository<TestDocPerson, String>
{


}
