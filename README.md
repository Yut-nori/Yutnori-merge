# Yutnori

# How to update frontend and backend by remote repository
## How to change FE repo
`git rm -r frontend`
`git commit -m "remove old frontend module"`
`git subtree add --prefix=frontend <FE repo> main --squash`
### Swing version
https://github.com/Yut-nori/Yutnori_FE.git
### FX version
https://github.com/Yut-nori/Yutnori_FE_FX.git


## frontend
`git fetch frontend`
`git subtree add --prefix=frontend frontend <branch name> --squash`

`git fetch backend`
`git subtree add --prefix=backend backend <branch name> --squash`

## Make jar file
`gradlew.bat :frontend:shadowJar`
