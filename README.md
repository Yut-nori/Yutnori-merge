﻿# Yutnori

# How to update frontend and backend by remote repository
# 🛠️ How to manage Frontend (Swing / FX) using Git Subtree

## 🧹 Remove old FE version (if any)

```bash
git rm -r frontend-swing  # or frontend-fx depending on what you replace
git commit -m "remove old frontend module"
```

---

## ➕ Add new FE version

### ▶ Swing version

```bash
git subtree add --prefix=frontend-swing https://github.com/Yut-nori/Yutnori_FE.git main --squash
```

🔗 [Swing Repository](https://github.com/Yut-nori/Yutnori_FE.git)

---

### ▶ FX version

```bash
git subtree add --prefix=frontend-fx https://github.com/Yut-nori/Yutnori_FE_FX.git main --squash
```

🔗 [FX Repository](https://github.com/Yut-nori/Yutnori_FE_FX.git)

---

## 🔄 Subtree fetch & re-attach (manual use)

### ▶ From frontend remote

```bash
git fetch frontend
git subtree add --prefix=frontend <branch-name> --squash
```

### ▶ From backend remote

```bash
git fetch backend
git subtree add --prefix=backend backend <branch-name> --squash
```

---

## ⚙️ Make JAR file

```bash
gradlew.bat :frontend:shadowJar
```

---

## 📝 Notes

- Use separate directories like `frontend-swing/` and `frontend-fx/` to avoid subtree conflict.
- Always use `--squash` unless you want to preserve full commit history from sub-repositories.

