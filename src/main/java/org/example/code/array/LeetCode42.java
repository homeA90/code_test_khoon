package org.example.code.array;

/*
문제 : 높이를 입력받아 비 온 후 얼마나 많은 물이 쌓일 수 있는지 계산하라.
입력 : [1,1,0,2,1,0,1,3,2,1,2,1]
출력 : [6]
 */

import java.util.ArrayDeque;
import java.util.Deque;

public class LeetCode42 {

    public static int trapTwoPoint(int[] height){

        // 문제 풀이 접근 방식은 투 포인트로 접근한다. 양쪽 끝에 2개의 포인트를 잡아 가운대로 한칸씩 이동한다.
        // 그런데 매번 배열의 오른쪽 왼쪽을 찾아서 하면 복잡도가 올라가니 양쪽의 최대높이를 기준으로 갱신하면 낮은쪽에 물이 고일 수 있는지 계산하는것이다

        // 핵심1. 필드 선언의 핵심은 leftMax와 rightMax 이다.
        // 물은 고일 수 있으려면 현재 위치의 왼쪽과 오른쪽에 "벽"이 있어야 한다.
        // 그리고 양쪽 벽 중 낮은 벽만큼 물이 찰 수 있다는 현실적인 원리를 반영해야한다.
        int volume = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = height[left];
        int rightMax = height[right];

        // 핵심2. while을 이용하여 left와 right가 가까워질 때까지 진행을 한다.
        // 매번 leftMax와 rightMax를 비교하여 최대 높이가 변경되었는지 확인하고 변경되면 새롭게 저장을 한다.
        while(left < right) {
            leftMax = Math.max(height[left], leftMax);
            rightMax = Math.max(height[right], rightMax);

            // 왼쪽을 기준으로 잡고 오른쪽 최대 높이가 왼쪽보다 높거나 같다는 전제를 한다면 왼쪽에는 물이 고일 수 있다.
            // 조건문을 통해 오른쪽 최대 높이는 일단 왼쪽 최대 높이보단 높으므로 물고이는 것을 왼쪽 최대 높이만 비교를 통해 알수있다.
            // 그러므로 left의 최대 높이에서 현재 인덱스의 높이를 뺄셈하여 현재 물이 고인 값을 알아낼 수 있다.
            // 같다면 0이 될테고 leftMax가 크다면 물이 계산 값만큼 찰 것이다.
            // 그리고 계산이 끝났다면 left 쪽을 한칸 전진시켜 다음 계산을 진행한다.
            // 반대로 else로 빠지면 leftMax > rightMax 가 되는 것이므로 right 기준으로 계산하고 right 를 왼쪽으로 후진? 시킨다.
            if (leftMax <= rightMax) {
                volume += leftMax - height[left];
                left += 1;
            } else {
                volume += rightMax - height[right];
                right -= 1;
            }
        }
        return volume;
    }

    // 이 풀이법은 스택을 이용하여 문제를 푸는 방식이다.
    public static int trapStack(int[] height) {

        // 핵심1. 우선 stack을 사용하고 해당 값들을 저장하기 위해 Deque를 사용하여 값들을 저장한다.
        Deque<Integer> stack = new ArrayDeque<>();

        // 고인 물의 수를 저장하기 위해 선언해준다.
        int volume = 0;

        // 핵심2. 문제자체가 전체 값을 순회해야하므로 for 문을 이용하여 전체 순회를 시작한다.
        // 팁 : for 문에서 동시에 i, i+1가 증가하지 않으므로 length -1을 하지 않는다!!
        // while 문을 통해 stack의 값이 비어 있지 않고 현재 height[i] 값이 이전 height의 값보다 큰지 비교한다.
        // 왜 그렇게 비교하냐면 현재 height[i]의 값이 오른쪽 벽이 될것이고, 이전에 저장된 값이 물이 고일수 있는 부분이 될수있기 때문이다.
        // 만약 현재 height[i] 값이 더 크다면 이전에 저장했던 인덱스 값을 pop()시킨다.
        // stack 에서 pop()은 조회가 아니고 추출 후 stack 에서 삭제한다. pop() 후에 다시 peek()을 한다면 그 이전 값이 조회된다.
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {

                Integer top = stack.pop();

                // 그리고 pop() 후에 왜 stack의 상태를 확인하냐면 왼쪽 벽의 값, 즉 stack에 조회할 값이 남아있어야 물이 고일수 있기 때문이다.
                if ( stack.isEmpty()){
                    break;
                }
                // 왼쪽, 오른쪽 벽이 모두 존재한다면 이제 물이 고일 수 있는 거리를 계산한다.
                // 물론 현재 코드에선 최대 3개까지 확인하고 계산하므로 개인적인 생각으론 항상 1이 나올것 같지만 그래도 물이 고일 수 있는 길이가 얼마가 되는지 계산한다.
                // 계산 식은 i는 현재 위치, 오른쪽벽의 인덱스 값! stack.peek은 물이 고일 수 있는 위치를 pop()후 peek 된 값, 즉 왼쪽 벽 인덱스
                // 물이 고일 수 있는 거리를 계산하기 위해선 -1을 해줘야 가운데 물이 고이는 인덱스를 맞출 수 있다.
                // 인덱스:   0   1   2
                //        [2] [0] [2]   → 높이
                //        왼벽 바닥 오벽
                // 둘 사이에 실제 "물 고일 칸"은 인덱스 1 하나뿐이므로 오벽 - 왼벽 -1 을 해줘야 바닥을 선택할 수있다.
                int distance = i - stack.peek() - 1;

                // 이제 물이 고인 값을 구해야한다. 물은 양쪽 벽중 작은 값 이상으로 물이 고일 수 없다.
                // 그맇기 때문에 오른쪽벽과 왼쪽벽 중 작은 값을 선택해서 물이 고이는 위치의 인덱스 값을  빼도록한다.
                // 그러면 물이 얼만큼 고여야 하는지 값이 나온다.
                int waters = Math.min(height[i], height[stack.peek()] - height[top]);

                // 물이 고인 값을 구하는데 이건 수학공식과 같이 밑변 곱하기 높이 하면 물의 고인양이 나오므로 아래 계산처럼 한다.
                volume += distance * waters;
            }
            // 그리고 while에 해당하지 않는다면 stack에 저장하고 다음 값을 진행하도록한다!
            stack.push(i);
        }
        // for 문까지 끝난다면 volume을 return 하여 값을 출력한다.
        return volume;
    }



    public static void main(String[] args) {
        int[] h = {1,1,0,2,1,0,1,3,2,1,2,1};
        int r = trapTwoPoint(h);
        System.out.println(r);
    }
}
