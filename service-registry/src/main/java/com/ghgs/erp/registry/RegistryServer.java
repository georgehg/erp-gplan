package com.ghgs.erp.registry;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegistryServer {

    public static void main(String[] args) {

        List<Cliente> cliente = new ArrayList<>();

        Map<Integer, Cliente> mapClientes = new HashMap<>();
        mapClientes.put(1, new Cliente(1, "George"));

        cliente.add(new Cliente(1, "George"));
        cliente.add(new Cliente(2, "Hyago"));

//        System.out.println(cliente.get(0));
//        System.out.println(cliente.get(1));
//        System.out.println(cliente.get(2));

        Cliente meuCliente = (Cliente) cliente.get(0);
        System.out.println(meuCliente);

        Cliente meuOutroCliente = (Cliente) cliente.get(3);
        System.out.println(meuCliente);

    }




    public int solution(String[] R) {
        int gridSize = R.length;

        int maxRowLength = Arrays.stream(R)
                .mapToInt(String::length)
                .max()
                .orElse(1);

        Square[][] grid = parseGrid(R, gridSize, maxRowLength);

        if (gridSize == 1 && maxRowLength == 1) {
            if (Square.EMPTY.equals(grid[0][0])) {
                return 1;
            } else {
                return 0;
            }
        }

        Integer[] startingPosition = new Integer[]{0,0};
        Direction startingDirection = Direction.EAST;

        Map<String, Set<Direction>> cleanedSquares = new HashMap<>();
        cleanedSquares.put(getPositionKey(startingPosition), Collections.singleton(startingDirection));

        doClean(grid, startingPosition, startingDirection, cleanedSquares);

        return cleanedSquares.keySet().size();
    }

    private String getPositionKey(Integer[] currentPosition) {
        return String.join(";", currentPosition[0].toString(),currentPosition[1].toString());
    }

    private void doClean(final Square[][] grid, final Integer[] currentPosition, final Direction currentDirection,
                         Map<String, Set<Direction>> cleanedSquares) {

        final Integer[] newPosition;
        final Direction newDirection;

        if (canMove(grid, currentPosition, currentDirection)) {
            newPosition = currentDirection.move(currentPosition);
            newDirection = currentDirection;

            if (cleanedSquares.getOrDefault(getPositionKey(newPosition), Collections.emptySet()).contains(currentDirection)) {
                return;
            }

            System.out.println("Position: " + getPositionKey(newPosition) + " - Direction: " + cleanedSquares.get(getPositionKey(newPosition)));

            cleanedSquares.compute(getPositionKey(newPosition), (key, value) -> (value == null) ?
                    Collections.singleton(newDirection) :
                    Stream.concat(value.stream(), Stream.of(newDirection))
                            .collect(Collectors.toSet()));

        } else {
            newPosition = currentPosition;
            newDirection = currentDirection.turn();
        }

        doClean(grid, newPosition, newDirection, cleanedSquares);
    }

    private Square[][] parseGrid(String[] R, int gridSize, int maxRowLength) {
        Square[][] grid = new Square[gridSize][maxRowLength];

        for (int lineIdx = 0; lineIdx < gridSize; lineIdx++) {
            for (int rowIdx = 0; rowIdx < maxRowLength; rowIdx++) {
                char[] chars = R[lineIdx].toCharArray();

                if (chars.length == maxRowLength) {
                    grid[lineIdx][rowIdx] = Square.find(Character.toString(chars[rowIdx]));

                } else {
                    if (rowIdx >= chars.length) {
                        grid[lineIdx][rowIdx] = Square.BLOCK;
                    } else {
                        grid[lineIdx][rowIdx] = Square.find(Character.toString(chars[rowIdx]));
                    }
                }

            }
        }

        return grid;
    }

    private Boolean canMove(Square[][] grid, Integer[] position, Direction direction) {
        Integer[] nextPosition = direction.move(position);

        if (nextPosition[0] < 0 || nextPosition[0] >= grid.length) {
            return false;
        }

        if (nextPosition[1] < 0 || nextPosition[1] >= grid[nextPosition[0]].length) {
            return false;
        }

        return Square.EMPTY.equals(grid[nextPosition[0]][nextPosition[1]]);
    }

    enum Square {
        EMPTY("."),
        BLOCK("X");

        private static class Holder {
            static Map<String, Square> MAP = new HashMap<>();
        }

        Square(String value) {
            Holder.MAP.put(value, this);
        }

        public static Square find(String val) {
            Square t = Holder.MAP.get(val);
            if(t == null) {
                throw new IllegalStateException(String.format("Unsupported type %s.", val));
            }
            return t;
        }

    }

    enum Direction {
        NORTH {
            @Override
            public Integer[] move(Integer[] position) {
                return new Integer[]{position[0] - 1, position[1]};
            }

            @Override
            public Direction turn() {
                return Direction.EAST;
            }
        },

        EAST{
            @Override
            public Integer[] move(Integer[] position) {
                return new Integer[]{position[0], position[1] + 1};
            }

            @Override
            public Direction turn() {
                return Direction.SOUTH;
            }
        },

        SOUTH{
            @Override
            public Integer[] move(Integer[] position) {
                return new Integer[]{position[0] + 1, position[1]};
            }

            @Override
            public Direction turn() {
                return Direction.WEST;
            }
        },

        WEST{
            @Override
            public Integer[] move(Integer[] position) {
                return new Integer[]{position[0], position[1] - 1};
            }

            @Override
            public Direction turn() {
                return Direction.NORTH;
            }
        };

        abstract public Integer[] move(Integer[] position);

        abstract public Direction turn();

    }

}
