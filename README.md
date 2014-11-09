### tdd-is-not-dead

1. Reproduce the rails application

```
$ docker build -t rails-tdd .
$ docker run --rm -i --name rails-start -v $(pwd):/usr/src/app -w /usr/src/app -p 3000:3000 rails-tdd /bin/sh
```

From Scratch:

```
$ rails new ruby-app
$ cd ruby-app
$ rails generate scaffold Employees name:string email:string
$ rails db:migrate
$ rails server &
```

Save this state:

```
$ # identify id of the container:
$ docker ps
$ # tag the container as a new version of the image:
$ docker commit <sha1> rails-tdd:bundle-installed
```

1. Run the rails application

```
$ docker run -d --name rails-server -v $(pwd):/usr/src/app -w /usr/src/app/ruby-app -p 3000:3000 rails-tdd:bundle-installed /usr/local/bin/rails server
```


## TODO

Repair shell prompt
