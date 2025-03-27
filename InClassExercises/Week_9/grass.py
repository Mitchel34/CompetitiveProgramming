def solve_case(n, l, w, sprinklers):
    segments = []
    for x, r in sprinklers:
        if r <= w/2:
            continue
        
        d = (r**2 - (w/2)**2) ** 0.5
        
        left = max(0, x - d)
        right = min(l, x + d)
        segments.append((left, right))
    
    if not segments:
        return -1
    
    segments.sort()
    
    current_pos = 0
    selected_count = 0
    i = 0
    
    while current_pos < l and i < len(segments):
        best_end = current_pos
        
        while i < len(segments) and segments[i][0] > current_pos:
            i += 1
        
        if i >= len(segments):
            break
        
        j = i
        while j < len(segments) and segments[j][0] <= current_pos:
            if segments[j][1] > best_end:
                best_end = segments[j][1]
            j += 1
        
        if best_end <= current_pos:
            return -1
        
        selected_count += 1
        current_pos = best_end
        
        i = j
    
    return selected_count if current_pos >= l else -1

while True:
    try:
        line = input().strip()
        if not line:
            break
            
        n, l, w = map(int, line.split())
        sprinklers = []
        for _ in range(n):
            x, r = map(int, input().split())
            sprinklers.append((x, r))
        
        result = solve_case(n, l, w, sprinklers)
        print(result)
    except:
        break