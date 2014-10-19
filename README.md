### tdd-is-not-dead

1. Reproduce the rails application

```
$ docker build -t rails-tdd .
$ docker run --rm -i --name rails-start -v $(pwd):/usr/src/app -p 3000:3000 rails-tdd /bin/sh
```

Once on the shell :

```
$ cd /usr/src/app
$ rails new ruby-app
$ bundle install --system
$ rails generate scaffold Employees name:string email:string
$ rails db:migrate
$ rails server &
```

## TODO

Repair shell prompt
