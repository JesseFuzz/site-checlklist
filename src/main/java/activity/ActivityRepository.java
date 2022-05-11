package activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import activity.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
	
}
