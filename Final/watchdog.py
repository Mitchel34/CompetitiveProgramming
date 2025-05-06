import sys

def solve_case(S, hatches):
    # mark hatch positions for quick skip
    has_hatch = [[False]*(S+1) for _ in range(S+1)]
    for x,y in hatches:
        has_hatch[x][y] = True

    for x in range(S+1):
        for y in range(S+1):
            if has_hatch[x][y]:
                continue
            # maximum squared distance needed to cover every hatch
            max_dist2 = 0
            for hx, hy in hatches:
                dx = x - hx
                dy = y - hy
                d2 = dx*dx + dy*dy
                if d2 > max_dist2:
                    max_dist2 = d2
            # squared clearance to nearest edge
            e = min(x, y, S-x, S-y)
            if max_dist2 <= e*e:
                return f"{x} {y}"
    return "poodle"

def main():
    data = sys.stdin.read().split()
    it = iter(data)
    T = int(next(it))
    out = []
    for _ in range(T):
        S = int(next(it)); H = int(next(it))
        hatches = [(int(next(it)), int(next(it))) for _ in range(H)]
        out.append(solve_case(S, hatches))
    sys.stdout.write("\n".join(out))

if __name__ == "__main__":
    main()