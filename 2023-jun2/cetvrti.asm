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
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
;
