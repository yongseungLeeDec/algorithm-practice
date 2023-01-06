#include <string>
#include <vector>

using namespace std;

// 음양 더하기
// https://school.programmers.co.kr/learn/courses/30/lessons/76501?language=cpp
int solution_problem1(vector<int> absolutes, vector<bool> signs) {
    int size = absolutes.size();
    int answer = 0;
    for (int i = 0; i < size; i++) {
        if (signs[i]) {
            answer += absolutes[i];
        }
        else
        {
            answer -= absolutes[i];
        }
    }
    return answer;
}