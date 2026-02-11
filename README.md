# MoProCo evaluation repository

## Scope

This repository contains case studies and raw evaluation data for *MoProCo*, a multi-paradigmatic approach and tool to combine model-driven software engineering, LLM-based software development, and coding.

MoProCo offers a unified textual syntax for UML-like class models. For operations and constructors, natural language specifications can be provided directly in the language. From MoProCo models, Java code is generated in a three-phase procedure:
1. Template-based *deterministic* generation of structural code (with all relevant special cases such as bidirectional associations, composition semantics, etc.). Operations are mapped to method stuby with empty bodies.
2. LLM-based *non-deterministic* generation of method bodies based on the structural context and the natural-language specification provided. To this end, MoProCo performs one LLM prompt per method and transfers the response into the generated Java code.
3. Human evaluation of generated code, with the option to re-generate specific methods based on refined prompts and to propagate back generated code fragments into the model in order to locally remove non-determinism.

The repository contains the artifacts, metric calculation scripts, and calculated metrics of the application of MoProCo to three different case studies. To allow for a detailed comparison of MoProCo to pure modeling, pure LLM-prompting and pure manual coding, suitable artifacts are provided for each case study. The presented metrics can be reproducibly derived from these artifacts.

A journal publication utilizing the raw data contained here for the evaluation of the MoProCo approach is currently under review. It will be linked here once accepted.

## Optional dependencies

To reproduce the results, you may install MoProCo, which is offered as two VSCode extensions:
- [MoProCo language and deterministic code generation support](https://github.com/tbuchmann/class-diag-langium)
- [MoProCo LLM-processor for non-deterministic code generation](https://github.com/tbuchmann/mdellmprocessor)

## Case studies

To evaluate MoProCo, we conducted three case studies, all from common examples from the software engineering research community:
- **airline**: An airline seat reservation system. Airlines manage flights, for which passengers can reserve seats.
- **library**: A management system for libraries. Libraries manage books and their copies, which can be reserved by customers.
- **shop**: An online shop system. A shop organizes articles and their available items in stock. Customers can place orders.

The case studies represent software engineering activities in design and implementation. The models, prompts and code are dedicated to the *business logic* of these fictional applications.

## Environment and parameters

For all experiments, the following environment was used:
- IDE client host
    - Intel i7-1365 CPU (12 cores), 32 GB RAM
    - OS: Linux Mint 22.3 x86_64
    - VSCodium version: 1.107.18627 ()
    - MoProCo version 0.0.2 (for both extensions, `class-diag-langium` and `mdellmprocessor`)
    - Java version: openjdk 25.0.2 2026-01-20
- LLM server (virtual machine)
    - Dell Precision 7690 Rack, Intel Xeon Gold 6448 (4 cores), 32 GB RAM, NVIDIA RTX 4000 Ada Generation (20 GB VRAM)
    - OS: Ubuntu 24.04.3 LTS x86_64
    - Ollama version 0.15.4
    - OpenWebUI version: v0.7.2
- LLM parameters
    - model: `devstral-small-2:24b` (fully executed on GPU)
    - quantization: `Q4_K_M`
    - context length (`num_ctx`): 8192
    - temperature: 0.15
    - GPU usage: 16.5 GiB (80%)
    - interaction mode: `generate` (as opposed to `chat`)
    - system prompt and user prompt template: see below

### System prompt
```
You are a Java code generator being the second part of a two-stage code generation pipeline after a template-based structural generation pass.
It is your task to (re-)generate the bodies of one specific method to implement behavior as specified.
In each prompt, you will receive:
1. the name of one method to implement along with its class,
2. a natural language specification for the implementation,
3. the relevant code base (including the class for the method to implement).
You may use all accessible fields and methods from the code base, but you must not assume the existence of methods not mentioned there. Do not generate additional methods. If you need standard library functionalities not imported yet into the method's class, use fully qualified class names in the body, e.g. `java.util.Date`.
For manipulating object state, use the provided `set`/`addTo`/`removeFrom` methods instead of directly accessing private fields, even in the context class.
In your response, provide only the method body **without** the signature. Start with a four-spaces indentation and add two spaces for each additional level. Do not provide any additional explanations. Do not use Markdown code fences.
Your response will be directly copied between the ``//generated start``and ``//generated end`` markers of the method to implement; do not include these markers in your response. The code must compile successfully afterwards.
```

### User prompt template
```
##Method to implement:
`${methodName}` of class `${className}`

##Specification:
"${specification}"

##Context:
${context}
```

## Artifacts

For each of the case studies, five types of artifacts have been created:
- **Classical UML diagrams** created with PlantUML. One class diagram per case study, plus one additional activity diagram per operation.
- **Black-box LLM prompts and code generated from them (five runs)**. The LLM prompts reflect the structure and behavior of the models for each case study. The runs were executed as separate prompts to OpenWebUI. We record the response, usage data, and additional observations for each run.
- **MoProCo models and code generated from them (five runs)**. The classical UML model structure has been replicated. Behavior has been added as natural language prompts. We record the output of the first, deterministic phase, as well as the completed source code, usage data, and additional observations for each run of the second, LLM-based phase of the generation process.
- **Test suites for MoProCo-generated code** We provide JUnit tests for each case study. For each operation specified using natural language, at least two test cases exist. (Test suites contained in different subdirectories of each case study are identical per run.)
- **Refined MoProCo models with back-propagated code fragments** For each case study, we propagate back the LLM-generated code (see phase 3) of the first of the five runs into a copy of the original MoProCo model.

### Quick links to artifacts per case study
|             | **classical UML** | **black-box LLM** | **MoProCo** | **Test suites** | **Back-propagated MoProCo**
|------------:|:------------------|:------------------|:------------|:----------------|:---------------------------|
| **airline** | [diagrams](airline/classical-uml/index.md) | [prompt](airline/blackbox-llm/prompt.md), generated code ([1](airline/blackbox-llm/1/src) [2](airline/blackbox-llm/2/src) [3](airline/blackbox-llm/3/src) [4](airline/blackbox-llm/4/src) [5](airline/blackbox-llm/5/src)), [observations](airline/blackbox-llm/observations.md) | [model](airline/moproco/airline.cdiag), generated code ([1](airline/moproco/1/src) [2](airline/moproco/2/src) [3](airline/moproco/3/src) [4](airline/moproco/4/src) [5](airline/moproco/5/src)), [observations](airline/moproco/observations.md) | [test suite](airline/moproco/1/test) | [model with back-propagated code for run 1](airline/moproco/1/airline.cdiag)
| **library** | [diagrams](library/classical-uml/index.md) | [prompt](library/blackbox-llm/prompt.md), generated code ([1](library/blackbox-llm/1/src) [2](library/blackbox-llm/2/src) [3](library/blackbox-llm/3/src) [4](library/blackbox-llm/4/src) [5](library/blackbox-llm/5/src)), [observations](library/blackbox-llm/observations.md) | [model](library/moproco/library.cdiag), generated code ([1](library/moproco/1/src) [2](library/moproco/2/src) [3](library/moproco/3/src) [4](library/moproco/4/src) [5](library/moproco/5/src)), [observations](library/moproco/observations.md) | [test suite](library/moproco/1/test) | [model with back-propagated code for run 1](library/moproco/1/library.cdiag)
| **shop**    | [diagrams](shop/classical-uml/index.md) | [prompt](shop/blackbox-llm/prompt.md), generated code ([1](shop/blackbox-llm/1/src) [2](shop/blackbox-llm/2/src) [3](shop/blackbox-llm/3/src) [4](shop/blackbox-llm/4/src) [5](shop/blackbox-llm/5/src)), [observations](shop/blackbox-llm/observations.md) | [model](shop/moproco/shop.cdiag), generated code ([1](shop/moproco/1/src) [2](shop/moproco/2/src) [3](shop/moproco/3/src) [4](shop/moproco/4/src) [5](shop/moproco/5/src)), [observations](shop/moproco/observations.md) | [test suite](shop/moproco/1/test) | [model with back-propagated code for run 1](shop/moproco/1/shop.cdiag)

## Metrics and Results

Based on these three case studies, the following metrics have been defined and measured (partly with the use of Python scripts for the reason of reproducibility):
- **Correctness**: The ability of MoProCo to generate Java code that is (1) accepted by the compiler and (2) successfully verified with automated tests. [Results](compilation_test_success.md) (manually created based on the output of the Java compiler and JUnit for each run of each case study)
- **Productivity**: The ratio between the size of (1) the initial MoProCo model, (2) the Java source code generated from it, (3) the PlantUML code required to construct an equivalent model using classical UML modeling, (4) the prompt for the black-box LLM approach, (5) the code generated from the LLM used as black-box, and (6) the refined MoProCo model including all back-propagated Java method bodies. A lines of code metric is used to this end (where blank lines, comments, and lines only containing `}` are not counted). [Results](loc.md) (generated by the evaluation script [loc.py](loc.py))
- **Cognitive load**: Given the MoProCo specification and an equivalent set of PlantUML models, this metric compares (1) the number of distinct element types (e.g., association) used, and (2) the number of model elements used in total. [Results](model_elements.md) (manually created based on the `cdiag` and `puml` files of each case study)
- **Runtime efficiency**: The ratio between the execution times (measured on the LLM server) for combined MoProCo prompts and black-box prompts; the former are added up per run per case study. [Results](runtimes.md) (generated by the evaluation script [runtimes.py](runtimes.py) based on the logs provided by MoProCo and OpenWebUI)
- **Sustainable token usage**: The ratio between the produced tokens for combined MoProCo prompts and black-box prompts; the former are added up per run per case study. [Results](tokens.md) (generated by the evaluation script [tokens.py](tokens.py) based on the logs provided by MoProCo and OpenWebUI)  
- **Sensitivity for non-determinism**: We define the *variance* of *n* (here: 5) similar versions of a code base as the aggregated size of the diffs of all pair-wise combinations, in relation to the code base size. A higher variance is an indicator for non-determinisim caused by repeated LLM inference with equal input. We consider the ratio of variances of the MoProCo vs. the black-box LLM code bases. [Results](variance.md) (generated by the evaluation script [variance.py](variance.py))

## Generative AI usage disclosure

Generative AI (`gpt-oss:120b-cloud` via Ollama and OpenWebUI) has been utilized for:
- The creation of evaluation scripts (`loc.py` and `variance.py`)
- The generation of test cases for the three case studies (using the intermediate Java code, not containing completed method bodies, as input)

## License

The data contained in this repository is released under a Creative Commons license. See [LICENSE](LICENSE).

## Contact

[Felix Schwägerl](mailto:felix.schwaegerl@oth-regensburg.de), OTH Regensburg  
[Thomas Buchmann](mailto:thomas.buchmann@hof-university.de), Hof University
