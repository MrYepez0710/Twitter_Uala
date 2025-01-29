package com.uala.twitter.service;

import com.uala.twitter.db.Follow;
import com.uala.twitter.db.Users;
import com.uala.twitter.repository.FollowRepository;
import com.uala.twitter.interfaces.IUsers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FollowServiceImplTest {

    @Mock
    private IUsers iUsers;

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private FollowServiceImpl followServiceImpl;

    public FollowServiceImplTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFollowUser_Success() {
        Integer userId = 1;
        String nicknameUser = "testUser";
        Users followUser = new Users();
        followUser.setId(2);
        followUser.setNickname(nicknameUser);
        when(iUsers.getUserByNickname(nicknameUser)).thenReturn(followUser);

        Follow follow = new Follow(userId, followUser.getId(), followUser.getNickname(), true);
        when(followRepository.saveAndFlush(any(Follow.class))).thenReturn(follow);

        boolean result = followServiceImpl.followUser(userId, nicknameUser);

        assertTrue(result);
        verify(iUsers, times(1)).getUserByNickname(nicknameUser);
        verify(followRepository, times(1)).saveAndFlush(any(Follow.class));
    }

    @Test
    void testFollowUser_UserNotFound() {
        Integer userId = 1;
        String nicknameUser = "nonExistentUser";
        when(iUsers.getUserByNickname(nicknameUser)).thenReturn(null);

        boolean result = followServiceImpl.followUser(userId, nicknameUser);

        assertFalse(result);
        verify(iUsers, times(1)).getUserByNickname(nicknameUser);
        verifyNoInteractions(followRepository);
    }
}