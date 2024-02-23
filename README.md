# TaskBeats - Aplicativo de Gerenciamento de Tarefas e Notícias
Bem-vindo ao TaskBeats, um aplicativo desenvolvido em Kotlin para Android, projetado para facilitar o gerenciamento de tarefas com operações CRUD (Create, Read, Update, Delete). Além disso, o aplicativo conta com um fragment dedicado para exibir notícias, obtidas através de uma API externa.


## :camera_flash: Screenshots
<!-- You can add more screenshots here if you like -->
<img src="/results/image_1.png" width="260">&emsp;<img src="/results/image_2.png" width="260">&emsp;<img src="/results/image_3.png" width="260">&emsp;<img src="/results/image_4.png" width="260">&emsp;<img src="/results/image_5.png" width="260">&emsp;<img src="/results/image_6.png" width="260">

## Recursos Principais

### Gerenciamento de Tarefas
* CRUD: Execute operações básicas de criar, ler, atualizar e excluir tarefas.
* Room Database: Utiliza a biblioteca Room para armazenar localmente as informações das tarefas de maneira eficiente e persistente.
* ViewModel: Implementação do padrão ViewModel para gerenciar e manter os dados da interface do usuário de forma eficiente durante mudanças de configuração.

### Notícias
* Fragment de Notícias: Um fragment integrado ao aplicativo que exibe notícias obtidas de uma API externa.
* Retrofit: Utilização da biblioteca Retrofit para realizar chamadas de API e obter os dados de notícias de forma assíncrona e eficiente.

### Arquitetura
* MVVM (Model-View-ViewModel): Organização clara e separação de responsabilidades entre Model, View e ViewModel para um código mais limpo e modular.
* LiveData e Data Binding: Utilização de LiveData para notificar as mudanças nos dados e Data Binding para vincular automaticamente os componentes da interface do usuário aos dados do ViewModel.

### Testes
* Testes Unitários: Implementação de testes unitários para garantir a robustez do código.
* mainDispatcherRule: Utilização da regra mainDispatcherRule para testes assíncronos de forma controlada.

## Como Configurar
1. Clone o repositório para o seu ambiente de desenvolvimento local.
2. Abra o projeto usando o Android Studio.
3. Execute o aplicativo em um dispositivo ou emulador Android.

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para enviar pull requests para melhorar o aplicativo.

## Author
Julia Fideles (follow me on [Linkedin](https://www.linkedin.com/in/juliafideles/))

## License
```
The MIT License (MIT)

Copyright (c) 2021 Roque Buarque Junior

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
