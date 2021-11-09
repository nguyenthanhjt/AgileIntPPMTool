package io.agileintelligence.ppmtool.repository;

import io.agileintelligence.ppmtool.domain.BackLog;
import org.springframework.data.repository.CrudRepository;

public interface BacklogRepository extends CrudRepository<BackLog, Long> {
}
