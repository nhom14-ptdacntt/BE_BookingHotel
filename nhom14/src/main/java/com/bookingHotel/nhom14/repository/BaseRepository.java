package com.bookingHotel.nhom14.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 * @param <myEntity>
 * @param <ID>
 */
@NoRepositoryBean
public interface BaseRepository<myEntity, ID extends Number> extends JpaRepository<myEntity, ID> {

    @Override
    public <S extends myEntity> S save(S entity);

    @Override
    public List<myEntity> findAll();

    @Override
    public Optional<myEntity> findById(ID id);

    @Override
    public void deleteById(ID id);

}