/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bookingHotel.nhom14.service;

import java.util.List;

/**
 *
 * @author KhanhDzai - https://www.facebook.com/khanhdepzai.pro/
 * @param <MyEntity>
 * @param <ID>
 */
public interface IServiceFind<MyEntity, ID extends Number> {

    public List<MyEntity> findAll();

    public MyEntity findById(ID id);
}
