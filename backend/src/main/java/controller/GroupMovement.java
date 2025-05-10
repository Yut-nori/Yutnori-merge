package controller;
import java.util.List;
import model.GroupUnit;
import model.Unit;
import model.board.Board;
import model.board.Position;
import model.Status;

public class GroupMovement {
    private GroupManager groupManager;

    public GroupMovement(GroupManager groupManager) {
        this.groupManager = groupManager;
    }

    // 인스턴스 메서드: 반드시 this.groupMovement.moveGroup(...) 로 호출해야 함
    public void moveGroup(GroupUnit group, int distance) {
        if (group == null) return;

        List<GroupUnit> groupList = groupManager.getGroup();
        Board board = BoardManager.getBoard();
        Position groupPosition = group.getCurrentPosition();

        System.out.println("Current Path : " + group.getCurrentPathID());

        if (distance > 0) {
            //일반 바깥쪽
            if (!groupPosition.isVertex() && (groupPosition.getIndex() == 0 || groupPosition.getIndex() <= board.getLastOuterPosNum())) {
                moveNormal(group, distance);
                // 현 위치 Center
            } else if (groupPosition.isCenter() || group.isCenterToStart()) {
                group.setCenterToStart(true);
                moveCenterToStart(group, distance);
                // Vertex의 경우
            } else if (groupPosition.isVertex()) {
                // 그룹이 미리 타고오던 path가 없는 경우
                if(!group.hasPath()) {
                    List<Position> myPath = board.getInnerPath(groupPosition.getIndex());
                    group.setPath(groupPosition.getIndex());

                    moveInnerPath(group, myPath, distance);
                } else {
                    //이미 내부로 움직이던 경우 도착점
                    System.out.println("Path end");
                    group.releasePath();
                    moveNormal(group, distance);

                }
            } else {
                // Path 중
                List<Position> myPath = board.getInnerPath(group.getCurrentPathID());
                moveInnerPath(group, myPath, distance);
            }
        } else {
            // 뒤로 한 칸 (빽도)
            if (group.isHistoryEmpty()) {
                return;
            }
            if (group.getCurrentPosition().getIndex() == 0 && group.getUnitGroup().get(0).getStatus() == Status.ON) {
                if (distance == -1) {
                    System.out.println("[빽도: 0 → 이전 경로로 복귀]");
                    group.popHistory(); // 0 제거

                    if (group.isHistoryEmpty()) {
                        // 1 → 0 → 빽도 → 또 빽도인 경우: 스택이 비었으면 0으로 강제 복귀
                        // [1 → 0 → 빽도 → 또 빽도] 상황
                        // 원래 1에서 왔다고 간주하고 다시 1로 되돌림
                        System.out.println("스택이 비었기 때문에 1에서 왔던 것으로 간주하여 1번으로 이동합니다.");
                        Position backToOne = board.getPosition(1);
                        group.pushHistory(backToOne);
                        group.setPosition(backToOne);
                        group.markNotPassedZero();
                    } else {
                        // 정상적으로 이전 경로로 이동
                        Position backPos = board.getPosition(group.peekHistory());
                        group.setPosition(backPos);
                    }
                } else {
                    // 빽도가 아니면 → 완주로 처리
                    group.markPassedZero();
                }
                return;
            }

            // 일반 빽도 처리
            System.out.println("[빽도]를 진행합니다.");
            group.popHistory();

            if (group.isHistoryEmpty()) {
                Position startPos = board.getPosition(0);
                group.pushHistory(startPos);         // 0 위치 기록
                group.setPosition(startPos);         // 0 위치 이동
            } else {
                Position backPos = board.getPosition(group.peekHistory());
                group.setPosition(backPos);
            }
            if(group.getCurrentPosition().getIndex() == 0){
                group.markPassedZero();
            }
        }
        group.printHistoryStack();
    }


    private void moveNormal(GroupUnit group, int distance) {
        Position current = group.getCurrentPosition();
        for (int i = 0; i < distance; i++) {
            current = current.getNext();
            group.pushHistory(current);
            if(current.getIndex() == 0){
                group.markPassedZero();
            }
        }

        for (Unit unit : group.getUnitGroup()) {
            if (unit.getStatus() == Status.READY) {
                unit.setStatus(Status.ON);
            }
        }
        group.setPosition(current);
    }

    private void moveCenterToStart(GroupUnit group, int distance) {
        Position p = group.getCurrentPosition();
        int remainDistance = distance;
        while(p.getIndex() != 0 && remainDistance > 0) {
            p = p.getAltNext();
            group.pushHistory(p);
            remainDistance--;
            if(p.getIndex() == 0) group.markPassedZero();
        }
        group.setPosition(p);
        if(remainDistance > 0) moveNormal(group, distance);
    }

    private void moveInnerPath(GroupUnit group, List<Position> myPath, int distance) {
        Position p = group.getCurrentPosition();
        int currentPathIdx = myPath.indexOf(p);
        int remainToPathEnd = myPath.size() - currentPathIdx - 1;
        if(remainToPathEnd > distance) {
            for(int i=1;i<=distance;i++) {
                group.pushHistory(myPath.get(currentPathIdx + i));
            }
            group.setPosition(myPath.get(currentPathIdx + distance));
        } else {
            int normalDistance = distance - remainToPathEnd;

            for(int i=1;i<=remainToPathEnd;i++) {
                group.pushHistory(myPath.get(currentPathIdx + i));
                if(myPath.get(currentPathIdx+i).getIndex() == 0) group.markPassedZero();
            }
            group.setPosition(myPath.get(myPath.size() - 1));
            if(normalDistance > 0) moveNormal(group, normalDistance);
        }
    }
}