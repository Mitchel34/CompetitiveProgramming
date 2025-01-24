while true:
    line = input()
    if line == '-1':break

score = 0
problems = [false]*26

time, prob, correct = line.split()
time = int(time)
prob = ord(prob) - ord('A')

if correct == 'wrong':
    if problems[prob] != -1:
        problems[prob] += 1
    else:
        if problems[prob] != -1:
            score+= time + 20*problems[prob]
            problems[prob] = -1
            #the number of problems solved and the total time measure (including penalties)
            print(problems.count(-1), score)