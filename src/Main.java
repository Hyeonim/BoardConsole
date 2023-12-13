import DAO.BoardDAO;
import DTO.Board;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Board board;
    static List<Board> boardList;
    static BoardDAO dao = new BoardDAO();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        int menuNo = 0;
        do {

            menu();
            menuNo = sc.nextInt();
            sc.nextLine();

            if (menuNo == 0) {
                System.out.println("프로그램이 종료되었습니다");
                break;
            }

            switch (menuNo) {
                case 1:
                    menuWrite();
                    break;

                case 2:
                    menuUpdate();
                    break;

                case 3:
                    menuDelete();
                    break;

                case 4:
                    menuSelect();
                    break;

                case 5:
                    menuList();
                    break;

                default:
                    System.out.println("입력값이 잘못되었습니다.");
                    System.out.println("다시 입력해주세요");
                    break;
            }

        }
        while (true);

    }

    public static void menu() {

        System.out.println("-------------게 시 판-------------");
        System.out.println("----------1. 게시글 쓰기-----------");
        System.out.println("----------2. 게시글 수정-----------");
        System.out.println("----------3. 게시글 삭제-----------");
        System.out.println("----------4. 게시글 조회-----------");
        System.out.println("----------5. 게시글 전체목록--------");
        System.out.println("----------0. 게시판 종료-----------");
        System.out.println("--> 메뉴 선택 : ");

    }

    public static void menuWrite() throws SQLException {
        System.out.println("======[글 쓰 기]======");
        System.out.println("제목 : ");
        String title = sc.nextLine();

        System.out.println("작성자 : ");
        String writer = sc.nextLine();

        System.out.println("내용 : ");
        String content = sc.nextLine();

        System.out.println("제목 : " + title);
        System.out.println("작성자 : " + writer);
        System.out.println("내용 : " + content);

        board = new Board(title, writer, content);
//        dao.insert(board);
        int result = dao.insert(board);
    }

    public static void menuUpdate() throws SQLException {
        System.out.println("======[글 수 정]======");

        System.out.println("게시글 번호 : ");
        int board_no = sc.nextInt();
        sc.nextLine();

        System.out.println("제목 : ");
        String title = sc.nextLine();

        System.out.println("작성자 : ");
        String writer = sc.nextLine();

        System.out.println("내용 : ");
        String content = sc.nextLine();

        board = new Board(title, writer, content);
        board.setBoard_no(board_no);
        dao.update(board);


    }

    public static void menuDelete() {
        System.out.println("======[글 삭 제]======");

        System.out.println("게시글 번호 : ");
        int board_no = sc.nextInt();
        sc.nextLine();

        dao.delete(board_no);

    }

    public static void menuSelect() {
        System.out.println("======[글 조 회]======");
        System.out.println("계시글 번호 : ");
        int board_no = sc.nextInt();
        sc.nextLine();

        board = dao.select(board_no);

        print(board);

    }

    public static void menuList() {
        System.out.println("======[전체 목록]======");
        boardList = dao.selectList();
//        System.out.println(boardList);

        printAll();

    }


    public static void printAll() {
        for (Board board : boardList) {
            print(board);
        }
    }

    public static void print(Board board) {

        if (board == null)
            return;
        String dataFormat = "yyyy/MM/dd";
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
        String regDate = sdf.format(board.getReg_date());
        String updDate = sdf.format(board.getUpd_date());
        String No = String.format("%-3s", board.getBoard_no());
        // %-3s는 왼쪽정렬로 3필드의 최소 너비를 나타

        System.out.print("[게시글 번호 : " + board.getBoard_no() + "]");
        System.out.print(" 작성일 : " + regDate);
        System.out.print(" 수정일 : " + updDate);
        System.out.print(" 제목 : " + board.getTitle());
        System.out.print(" 작성자 : " + board.getWriter());
        System.out.println(" 내용 : " + board.getContent());



    }


}