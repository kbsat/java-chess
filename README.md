# java-chess

체스 미션 저장소

## 페어 규칙
- 어려워도 TDD로 구현한다.
- 역할 교대는 10분 간격으로 한다.
- 4사이클(40분)하고 10분 휴식을 취한다.
- 10시 이후에 개인정비 시간을 가진다.
- 의견 조율 시, 시간을 멈추고 5분정도 토론하고 결론이 안나면 가위바위보로 정한다. 패자는 묵묵히 따른다.
- 커밋 컨벤션은 아래와 같이 작성한다.
    - feat: commit message(한글)
- 매일 끝나기 10분 전에 당일 회고를 작성한다.
    - 칭찬과 격려를 마구마구 해준다.
    - 기분이 상하면 그자리에서 바로 푼다.

## 체스 규칙

### 체스판
- 체스판은 8x8 이다.
- 세로는 아래부터 위로 1부터 8까지이다.
- 가로는 왼쪽부터 오른쪽으로 a부터 h까지이다.
- 체스 말 배치도(검은색-대문자과 흰색-소문자)
~~~
RNBQKBNR  8 (rank 8)
PPPPPPPP  7
........  6
........  5
........  4
........  3
pppppppp  2
rnbqkbnr  1 (rank 1)

abcdefgh
~~~
### 체스 말
- 폰
  - 앞으로 1칸 갈 수 있다.(처음 움직일 때는 2칸까지 갈 수 있다.)
  - 상대 말을 잡을 때 앞쪽 대각선 방향으로만 잡을 수 있다.
  - 상대쪽 첫 번째 열 (1 또는 8)에 도착하면 킹을 제외한 다른 체스 말로 변경할 수 있다.
  - 각 팀의 폰은 총 8개이다.
- 나이트
  - 상하좌우로 두 칸 간 뒤에 양 옆으로 움직일 수 있다.
  - 각 팀의 나이트는 총 2개이다.
- 록
  - 상하좌우로 쭉 갈 수 있다.
  - 각 팀의 록은 총 2개이다.
- 비숍
  - 네 방향의 대각선으로 쭉 갈 수 있다.
  - 각 팀의 비숍은 총 2개이다.
- 퀸
  - 상하좌우, 네 방향의 대각선으로 쭉 갈 수 있다.
  - 각 팀의 퀸은 총 1개이다.
- 킹
  - 죽으면 게임이 끝난다.
  - 주변 8칸으로 움직일 수 있다.
  - 각 팀의 킹은 총 1개이다.

## 1단계 요구사항
- [x] 체스 게임을 시작할 지 묻는다.
  - `start`를 입력 시 체스판을 초기화하여 출력한다.
  - `end`를 입력 시 게임을 종료한다.
- [x] 체스판을 초기화한다.
  - 가로 세로 총 8칸이다.
- [x] 체스 말을 체스판 위에 놓는다.
  - 화면 기준으로 위쪽이 검은색(대문자), 아래쪽(소문자)이 흰색이다.



## 우아한테크코스 코드리뷰

- [온라인 코드 리뷰 과정](https://github.com/woowacourse/woowacourse-docs/blob/master/maincourse/README.md)
