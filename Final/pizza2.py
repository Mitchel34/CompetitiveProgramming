def main():
    R, C = map(int, input().split())
    # inner radius that actually has cheese
    r = R - C
    if r <= 0:
        # crust covers entire pizza
        print("0.000000")
    else:
        # ratio of areas = (r²)/(R²); multiply by 100 for percent
        percent = 100 * (r*r) / (R*R)
        # print with 6 decimal places (1e-6 accuracy)
        print(f"{percent:.6f}")

if __name__ == "__main__":
    main()