package com.example.zxy.data

import kotlinx.coroutines.flow.Flow

class OfflineUsersRepository(private val userDao: UserDao): UserRepository {
    override fun getAllUsersStream(): Flow<List<User>> =userDao.getAllItems()

    override fun getUserStream(id: Int): Flow<User?> = userDao.getItem(id)

    override suspend fun insertUser(user: User)= userDao.insert(user)

    override suspend fun deleteUser(user: User)= userDao.delete(user)

    override suspend fun updateUser(user: User)= userDao.update(user)
}