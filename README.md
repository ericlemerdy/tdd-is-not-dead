### Use bower in docker:
```
docker start dockerfile/nodejs-bower-grunt
docker run -i -v $(pwd):/data dockerfile/nodejs-bower-grunt /bin/sh
```

### From the container, download impress:
```
bower --allow-root install impress-js
```

### Todo

 - relire les entrées de blog pour synthétiser
 - terminer les slides d'arguments
 - trouver un exemple de code C1 et C1Test ok ; C2 utilise C1 ; on commence par mocker ; puis on vire le mock
 - trouver un exemple de code C1, C1Test, C2, C2Test ok ; Main utilise C1 et C2 mais plante
 - implémenter Employees en fluenthttp TDD