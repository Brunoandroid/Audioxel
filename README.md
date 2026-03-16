# Audioxel 🎵

Audioxel é um reprodutor de música moderno para Android, desenvolvido com as tecnologias mais recentes do ecossistema mobile. O projeto utiliza a API do SoundCloud para busca e reprodução de faixas, oferecendo uma interface limpa e intuitiva baseada em Material Design 3.

> **Nota sobre o desenvolvimento:** Este projeto está sendo preparado para comercialização em breve. Devido a isso, algumas funcionalidades avançadas ou específicas podem não estar disponíveis publicamente neste repositório no momento.

## Layout mobile
<div align="center">
  <table>
    <tr>
      <td><img src="https://github.com/Brunoandroid/Imagens/blob/main/audioxel_home.gif" width="250" height="500"/></td>
      <td><img src="https://github.com/Brunoandroid/Imagens/blob/main/audioxel_details_full.gif" width="250" height="500"/></td>
    </tr>
    <tr>
      <td align="center"><b>Home</b></td>
      <td align="center"><b>Lista de Músicas</b></td>
    </tr>
  </table>
</div>

## ✨ Características

- **Exploração de Músicas:** Busque por usuários e descubra suas faixas.
- **Interface Moderna:** Desenvolvida inteiramente com Jetpack Compose e Material 3.
- **Player Integrado:** Experiência de áudio fluida utilizando Media3 ExoPlayer.
- **Segurança:** Armazenamento seguro de tokens e credenciais.
- **Arquitetura Robusta:** Segue os princípios de Clean Architecture e MVVM.

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** [Kotlin](https://kotlinlang.org/)
- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Injeção de Dependência:** [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Rede:** [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
- **Player de Áudio:** [Media3 ExoPlayer](https://developer.android.com/guide/topics/media/media3)
- **Carregamento de Imagem:** [Coil](https://coil-kt.github.io/coil/)
- **Navegação:** [Compose Navigation](https://developer.android.com/jetpack/compose/navigation)

## 🚀 Como Começar

### Pré-requisitos

- Android Studio Koala ou superior.
- JDK 11+.
- Credenciais da API do SoundCloud.

### Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/audioxel.git
   ```

2. Renomeie o arquivo `gradle.properties-example` para `gradle.properties` na raiz do projeto.

3. Adicione suas credenciais do SoundCloud no arquivo `gradle.properties`:
   ```properties
   SOUNDCLOUD_CLIENT_ID="SUA_CLIENT_ID"
   SOUNDCLOUD_CLIENT_SECRET="SUA_CLIENT_SECRET"
   ```

4. Sincronize o projeto com os arquivos Gradle e execute o aplicativo.

## 🏗️ Estrutura do Projeto

O projeto está organizado seguindo as melhores práticas de modularização por funcionalidade:

- `audio`: Lógica de reprodução e gerenciamento do ExoPlayer.
- `components`: Componentes de UI reutilizáveis.
- `data`: Repositórios, modelos e fontes de dados (Remote/Local).
- `di`: Módulos de Injeção de Dependência.
- `navigation`: Definições de rotas e grafo de navegação.
- `screens`: Telas e ViewModels da aplicação.
- `ui.theme`: Definições de cores, tipografia e tema do sistema.
