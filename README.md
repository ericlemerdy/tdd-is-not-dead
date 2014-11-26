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
