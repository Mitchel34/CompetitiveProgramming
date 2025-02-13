

fline = input().split()
sline = input()

origMolecule = fline[0]
origMoleculeCount = int(fline[1])

def getMoleculeCounts(fromMolecule, multiplier):
    moleculeCounts = {}
    molecule = ''
    currentCount = ''
    for i in range(len(fromMolecule)):
        if fromMolecule[i].isupper():
            if molecule != '':
                if molecule not in moleculeCounts:
                    moleculeCounts[molecule] = 0
                moleculeCounts[molecule] += int(currentCount) if currentCount != '' else 1
            currentCount = ''
            molecule = fromMolecule[i]
        else:
            currentCount += fromMolecule[i]
    
    if molecule not in moleculeCounts:
        moleculeCounts[molecule] = 0
    moleculeCounts[molecule] += int(currentCount) if currentCount != '' else 1

    for key in moleculeCounts.keys():
        moleculeCounts[key] *= multiplier
    return moleculeCounts

originalMoleculeCounts = getMoleculeCounts(origMolecule, origMoleculeCount)
desiredMoleculeCounts = getMoleculeCounts(sline, 1)

minMaxCount = 10000000000
for key in desiredMoleculeCounts:
    if key not in originalMoleculeCounts:
        minMaxCount = 0
        break
    minMaxCount = min(minMaxCount, originalMoleculeCounts[key] // desiredMoleculeCounts[key])
print(minMaxCount)