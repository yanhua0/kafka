package com.zjl.pgsql.repository;

import com.zjl.pgsql.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zoujulin [zou.julin@unisinsight.com]
 * @date 2020/12/07 11:04
 * @since 1.0
 */
public interface TestRepository extends JpaRepository<Test,Integer> {
}
