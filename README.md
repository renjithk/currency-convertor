Currency Convertor
=================

This project is built on MVP architecture using [Dagger 2](https://google.github.io/dagger/) and [ButterKnife](https://github.com/JakeWharton/butterknife). Inter-layer communication is enabled with the help of [EventBus](https://github.com/greenrobot/EventBus). 

Assumptions 
-----------

During the development of this project, the following assumptions are made.

* Number of possible transactions under a products is limited to range of Integer
* Separate view adapters are used to inflate row view on Product and Transaction Details list
* EventBus library is used to perform background operations. AsyncTask is implicitly omitted in the use case
