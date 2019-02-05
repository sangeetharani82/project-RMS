package org.launchcode.projectRMS.models.data;

import org.launchcode.projectRMS.models.RateComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface RateCommentDao extends CrudRepository<RateComment, Integer> {
}
