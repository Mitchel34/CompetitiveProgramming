use std::io;
use std::io::BufRead;
fn main() {
    let stdin = io::stdin();
    let mut lines = stdin.lock().lines();
    
    let input = lines.next().unwrap().unwrap();
    let n: i32 = input.trim().parse().expect("not a number");
    
    let input = lines.next().unwrap().unwrap();
    let mut v: Vec<i64> = input
        .trim()
        .split_whitespace()
        .map(|s| s.parse().unwrap())
        .collect();

    v.sort_by(|a,b| b.cmp(a));
    let mut total = 0;
    
    for i in 0..v.len() {
        if i % 3 == 2{
            total += v[i];
        }    
    }
    
    println!("{}", total);