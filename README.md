# Yutnori

# How to update frontend and backend by remote repository
## How to change FE repo (Swing / FX separated)

### Remove old FE version (if any)
```bash
git rm -r frontend-swing  # or frontend-fx depending on what you replace
git commit -m "remove old frontend module"

### Add new FE version
```bash
git subtree add --prefix=frontend-swing https://github.com/Yut-nori/Yutnori_FE.git main --squash
git subtree add --prefix=frontend-fx https://github.com/Yut-nori/Yutnori_FE_FX.git main --squash





## frontend
`git fetch frontend`
`git subtree add --prefix=frontend frontend <branch name> --squash`

`git fetch backend`
`git subtree add --prefix=backend backend <branch name> --squash`

## Make jar file
`gradlew.bat :frontend:shadowJar`
