;  struct model {                          int render_scene(struct model* m) {
;    int id;             // base + 0           int loaded = 0;
;    int type;           // base + 2           loaded = loaded + load_model(m->id, m->type);
;    struct model* next; // base + 4           if(m->next) {
;  }                                               loaded += render_scene(m->next);
;                                              }
;                                              return loaded;
;                                           }

; Stack frame za render_scene
;
;   ┌─────────────────┐
;   │                 │
;   │        m        │<--- ebp + 4
;   │                 │
;   ├─────────────────┤
;   │                 │
;   │  return address │<--- ebp + 2
;   │                 │
;   ├─────────────────┤
;   │                 │
;   │  old ebp        │<--- ebp
;   │                 │
;   ├─────────────────┤
;   │                 │
;   │  loaded         │<--- ebp - 2
;   │                 │
;   └─────────────────┘
;
render_scene:
  push ebp     ;
  mov ebp, esp ;  init stack frame
  sub esp, 0x2 ;

  ; prepare stack for load_model call
  ; return value
  mov [ebp-0x2], 0
  mov eax, 0
  push eax
  ; load m
  mov eax, [ebp+0x4]
  ; m->type
  mov ebx, [eax+0x2]
  push ebx
  ; m->id
  mov ebx, [eax]
  push ebx 
  call load_model
  add esp, 0x4
  pop eax ; eax = result of load_model
  ; loaded = loaded + result
  mov ebx, [ebp-0x2]
  add ebx, eax
  mov [ebp-0x2], ebx
  ; if
  mov eax, [ebp+0x4]
  mov eax, [eax+0x4]
  test eax, eax ; set zero flag to 1 if eax = 0
  jz kraj
  ; prepare stack for render_schene
  push eax
  call render_scene
  ; loaded = loaded + render_scene(m->next)
  pop eax ; clean call stack
  mov eax, [ebp-0x2]
  add eax, ecx
  mov [ebp-0x2], eax
  kraj:
  mov ecx, [ebp-0x2]
  ; return
  mov esp, ebp
  pop ebp
  ret
