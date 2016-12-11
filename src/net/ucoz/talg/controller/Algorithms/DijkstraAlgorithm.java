package net.ucoz.talg.controller.Algorithms;

import net.ucoz.talg.model.AdjacencyListItem;
import net.ucoz.talg.model.Graph;
import net.ucoz.talg.model.GraphNode;
import net.ucoz.talg.utils.MyLinkedList;
import net.ucoz.talg.utils.MyPriorityQueue;

import java.util.*;

public class DijkstraAlgorithm implements PathFindingAlgorithm {
    private Graph graph;
    private GraphNode[] nodes;
    private List<AdjacencyListItem>[] adjacencyList;

    private MyPriorityQueue<GraphNode> visitedCells;
    private MyLinkedList<Cell> distances; //  тут храним длину кратчайшего пути для каждой вершины


    @Override
    public LinkedList<GraphNode> getPath(Graph mapGraph, int startingPoint, int endPoint) {
        this.graph = mapGraph;
        this.nodes = this.graph.getNodes();
        this.adjacencyList = this.graph.getAdjacencyList();

        LinkedList<GraphNode> path = new LinkedList<>();
        getShortestPathBetweenTwoNodes(startingPoint, endPoint, path);

        return path;
    }

    /**
     * Найти кратчайший путь между указанными вершинами.
     *
     * @param indexNodeStart  Начальная вершина
     * @param indexNodeFinish Конечная вершина
     * @param path            Список вершин в пути
     */
    private void getShortestPathBetweenTwoNodes(int indexNodeStart, int indexNodeFinish, LinkedList<GraphNode> path) {

        GraphNode startNode = nodes[indexNodeStart];
        GraphNode finishNode = nodes[indexNodeFinish];

        distances = new MyLinkedList<>();
        visitedCells = new MyPriorityQueue<>((o1, o2) -> {
            Cell cell1 = distances.getElement(o1.getNodeIndex());
            Cell cell2 = distances.getElement(o2.getNodeIndex());

            if (cell1.cost != cell2.cost){
                return cell1.cost < cell2.cost ? 1 : -1;
            }
            return 0;
        });

        for (GraphNode node : nodes) {
            node.setVisited(false);
        }

        // изначально вес всех узлов равен бесконечности
        for (GraphNode node : nodes){
            distances.insertElement(new Cell(node, Integer.MAX_VALUE));
        }

        // текущий узел = стартовая вершина
        GraphNode currentNode = startNode;
        // вес стартовой вершины равен 0
        distances.insertElementAt(new Cell(currentNode, 0), 0);
        // добавляем стартовую вершину в список посещенных клеток
        visitedCells.enqueue(currentNode);

        // пока список посещенных клеток не пуст
        while (!visitedCells.isEmpty()) {
            currentNode.setVisited(true);
            // текущий узел с наибольшим приоритетом(наименьший вес) извлекается из списка посещенных клеток
            currentNode = visitedCells.dequeue();
            Cell currentCell = distances.getElement(currentNode.getNodeIndex());

            // если текущий узел = конечной вершине, то перерасчет веса закончен
            if (currentNode == finishNode){
                break;
            }

            // список соседей текущего узла
            List<AdjacencyListItem> neighbours = adjacencyList[currentNode.getNodeIndex()];

            // для каждого сесднего узла
            for (AdjacencyListItem neighbourItem : neighbours) {
                GraphNode neighbour = neighbourItem.getNode();
                double weight = neighbourItem.getWeight();

                // если соседний узел уже посещен, то продолжить дальше
                if (neighbour.isVisited()){
                    continue;
                }

                Cell neighbourCell = distances.getElement(neighbour.getNodeIndex());
                // перерасчет цены (цена текущей вершины + вес соседнего узла)
                double newCost = currentCell.cost + weight;

                // если соседний узел не был посещен или
                // если новая цена соседнего узла меньше старой
                if (!visitedCells.contains(neighbour) || newCost < neighbourCell.cost) {
                    if (!visitedCells.contains(neighbour)) {
                        // добавить соседний узел в список посещенных клеток
                        visitedCells.enqueue(neighbour);
                    }

                    // цена соседа = цене, которая была перерасчитана
                    neighbourCell.cost = newCost;
                    // текущая клетка - родитель соседней ей клетки
                    neighbourCell.parentNode = currentNode;
                }
            }
        }

        // если мы так и не дошли до конечной вершины, то путь не найден
        if (currentNode != finishNode) {
            System.out.println("Путь не найден!");
            return;
        }

        // добавить текущую клетку(конечная вершина) в искомый короткий путь
        path.add(currentNode);

        // пока мы не дошли до стартовой вершины
        while (currentNode != startNode) {
            currentNode = distances.getElement(currentNode.getNodeIndex()).parentNode;
            path.add(currentNode);
        }

        // поскольку путь у нас записываля из финишной клетки в стартовую,
        // то следует изменить направление, т.е. из стартовой клетки в финишную
        Collections.reverse(path);
    }

    private class Cell {
        GraphNode parentNode;
        GraphNode node;
        double cost;

        Cell(GraphNode node, GraphNode parentNode, double cost) {
            this.node = node;
            this.parentNode = parentNode;
            this.cost = cost;
        }

        Cell(GraphNode node, double cost) {
            this(node, null, cost);
        }
    }

}
