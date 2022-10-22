package ru.spring.P50519.Repository;

import org.springframework.data.repository.CrudRepository;
import ru.spring.P50519.Models.Hobbi;

public interface HobbiRepository extends CrudRepository<Hobbi,Long> {
   Hobbi findByLocation(String name);

}
