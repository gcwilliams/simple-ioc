### Simple IOC

#### A simple IOC container

A very simple IOC written for the fun of it...

* Constructor injection only
* No need for annotations

#### Usage

Create instance...
`IOCBuilder builder = IOCBuilder.newInstance();`

Add bindings...
`builder.bind(Interface.class).to(Implementation.class);`

Build...
`IOC ioc = builder.build();`

Resolve...
`Interface injected = ioc.resolve(Interface.class);`