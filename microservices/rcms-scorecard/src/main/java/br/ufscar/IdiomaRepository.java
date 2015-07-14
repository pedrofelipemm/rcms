package br.ufscar;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface IdiomaRepository extends JpaRepository<Idioma, Long>, IdiomaRepositoryCustom {


}