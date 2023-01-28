# Prueba Rappi para Android  

- Esteban Barrios Android Developer

## Responda y escriba dentro del Readme con las siguientes preguntas:

1. ¿En qué consiste el principio de responsabilidad única? ¿Cuál es su propósito?
    - El principio de responsabilidad única consiste en que cada clase que se cree debe tener una única responsabilidad, 
    es decir que cada clase debe ser creada con un objetivo especifico y debe ejecutar funcionalidades proporcionadas por el software.
    El proposito de este principio es separar los comportamientos de tal manera que en una clase se debe contener una única 
    razón para cambiar, si una clase contiene mas de un mótivo de cambio entonces se deben dividir las responsabilidades.    

2. ¿Qué características tiene, según su opinión, un “buen” código o código limpio?
  - En mi opinión un código limpio debe tener una arquitectura propuesta, debe estar basado en patrones y principios de desarrollo de software tales como :
      - Cada porción del código se debe encargar de una funcionalidad especifica (Principio de responsabilidad única)
      - El código se debe leer fácil (principio KISS)
      - El código no se debe repetir (principio DRY)
      - El código debe ser descriptivo nombrando clases, funciones, recursos de manera clara y concisa.
      - Debe tener pruebas unitarias.
      - Debe depender los menos posible de librerías y dependencias externas.
      - El código debe ser legible.
      - El código debe serguir convenciones estandar.
    
3. Detalla cómo harías todo aquello que no hayas llegado a completar. 
  - Se completo la prueba tal cual como se requeria en la prueba.
  - Si esta es una app que se fuera subir a la Play Store deberiamos hacer lo siguiente :
    - Instalar Crashlitycs o sistema de registro de errores.
    - Ofuscar el código colocando las reglas de proguard.
    - Crear files de integración continua para ejecutar pipeline de pruebas, linter.
    - Crear files para hacer despliegue automatico en FirebaseDistribution por medio de BitRise o algun otro sistema para permitir las pruebas al equipo de QA. 
      
  Todas esta crácteristicas a traves de la experiencia las he podido comprobar en diferentes sistemas con la aplicación de código limpio y de buenas prácticas sugeridas por Bob Martin "El tio Bob."
   
   
# Clean Code Architecture

Para el desarrollo de este proyecto utilice clean architecture.

