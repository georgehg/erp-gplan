package com.ghgs.erp.registry;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistryServerTest {

    @Test
    public void cleaningRobot_shouldReturn1() {
        int solution = new RegistryServer().solution(new String[]{"."});

        assertEquals(1, solution);
    }

    @Test
    public void cleaningRobot_shouldReturn0() {
        int solution = new RegistryServer().solution(new String[]{"X"});

        assertEquals(0, solution);
    }

    @Test
    public void cleaningRobot_cleanedRoom1() {
        int solution = new RegistryServer().solution(new String[]{"...X..", "....XX", "..X..."});

        assertEquals(6, solution);
    }

    @Test
    public void cleaningRobot_cleanedRoom2() {
        int solution = new RegistryServer().solution(new String[]{"....X..", "X......", ".....X.", "......."});

        assertEquals(15, solution);
    }

    @Test
    public void cleaningRobot_cleanedRoom3() {
        int solution = new RegistryServer().solution(new String[]{"...X.", ".X..X", "X...X", "..X.."});

        assertEquals(9, solution);
    }

    @Test
    public void cleaningRobot_cleanedRoomWithRole() {
        int solution = new RegistryServer().solution(new String[]{".......", "X...X..", ".....X.", "......."});

        assertEquals(21, solution);
    }

}
