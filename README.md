# Tutorial stackspot

- [doc oficial](https://docs.legacy.stackspot.com/docs/create-stacks/quickstart/)

## Passo-aPasso

- criar a stack: `stk-legacy create stack test-stack`
- criar um template `stk-legacy create template base-template`
- rodar o tempalte: `stk-legacy create app test-app -p base-template`
- rodar plugin: `stk-legacy apply plugin -p ./../mediator`

- criar uma stack: `stk-legacy create stackfile processMediator`

- importar uma stack: `stk-legacy import stack %cd%\jornada-stackspot`

- criar uma app apartir de uma stack: `stk-legacy create app test-app --stackfile test-stack/processMediator`

## [Jinja](https://jinja.palletsprojects.com/en/3.0.x/templates/)

## config

- [jornada stackspot](https://docs.google.com/presentation/d/17mDTe4liyWYC9v37YuGjc_Mdv71Dff_e/edit#slide=id.p12)
- [configurar github](http://rede-commons.pages.gitlab.prd.useredecloud/techstacks/documentation/stackspot/#solita%C3%A7%C3%A3o-de-acesso)
- [vídeo de introdução ao stackspot](https://www.youtube.com/watch?v=WEXjmMqvgfk)