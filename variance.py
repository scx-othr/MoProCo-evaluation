import os
import itertools
import difflib

def collect_files(root):
    """
    Return a dict mapping relative paths to full paths.
    """
    files = {}
    for dirpath, _, filenames in os.walk(root):
        for fn in filenames:
            rel = os.path.relpath(os.path.join(dirpath, fn), root)
            files[rel] = os.path.join(dirpath, fn)
    return files

def read_or_empty(path):
    """
    Read file lines or return empty list if file doesn't exist.
    """
    try:
        with open(path, encoding="utf-8", errors="ignore") as f:
            return f.readlines()
    except FileNotFoundError:
        return []

def diff_size(lines1, lines2):
    """
    Return a simple diff size metric: number of differing lines.
    """
    diff = difflib.unified_diff(lines1, lines2)
    # Count lines starting with + or - (excluding diff metadata)
    count = sum(1 for l in diff if l.startswith("+ ") or l.startswith("- "))
    return count

def compute_pairwise_diff(folders):
    """
    Compute average pairwise diff across all folder pairs.
    """
    # Collect all distinct relative paths
    all_files = set()
    file_maps = [collect_files(f) for f in folders]
    for fm in file_maps:
        all_files |= fm.keys()

    diffs = []
    for (i, j) in itertools.combinations(range(len(folders)), 2):
        total = 0
        for rel in all_files:
            l1 = read_or_empty(file_maps[i].get(rel, ""))
            l2 = read_or_empty(file_maps[j].get(rel, ""))
            total += diff_size(l1, l2)
        diffs.append(total)

    avg = sum(diffs) / len(diffs) if diffs else 0
    return avg, diffs

if __name__ == "__main__":
    folders = [
        "/path/to/src1",
        "/path/to/src2",
        "/path/to/src3",
        "/path/to/src4",
        "/path/to/src5",
    ]
    avg, diffs = compute_pairwise_diff(folders)
    print("Pairwise diffs:", diffs)
    print("Average diff (your variance):", avg)
