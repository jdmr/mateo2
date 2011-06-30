package inventario



import org.junit.*
import grails.test.mixin.*


@TestFor(CancelacionAlmacenController)
@Mock(CancelacionAlmacen)
class CancelacionAlmacenControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/cancelacionAlmacen/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.cancelacionAlmacenInstanceList.size() == 0
        assert model.cancelacionAlmacenInstanceTotal == 0

    }

    @Test
    void testCreate() {
       def model = controller.create()

       assert model.cancelacionAlmacenInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.cancelacionAlmacenInstance != null
        assert view == '/cancelacionAlmacen/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/cancelacionAlmacen/show/1'
        assert controller.flash.message != null
        assert CancelacionAlmacen.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cancelacionAlmacen/list'


        def cancelacionAlmacen = new CancelacionAlmacen()

        // TODO: populate domain properties

        assert cancelacionAlmacen.save() != null

        params.id = cancelacionAlmacen.id

        def model = controller.show()

        assert model.cancelacionAlmacenInstance == cancelacionAlmacen
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cancelacionAlmacen/list'


        def cancelacionAlmacen = new CancelacionAlmacen()

        // TODO: populate valid domain properties

        assert cancelacionAlmacen.save() != null

        params.id = cancelacionAlmacen.id

        def model = controller.edit()

        assert model.cancelacionAlmacenInstance == cancelacionAlmacen
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cancelacionAlmacen/list'

        response.reset()


        def cancelacionAlmacen = new CancelacionAlmacen()

        // TODO: populate valid domain properties

        assert cancelacionAlmacen.save() != null

        // test invalid parameters in update
        params.id = cancelacionAlmacen.id

        controller.update()

        assert view == "/cancelacionAlmacen/edit"
        assert model.cancelacionAlmacenInstance != null

        cancelacionAlmacen.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/cancelacionAlmacen/show/$cancelacionAlmacen.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/cancelacionAlmacen/list'

        response.reset()

        def cancelacionAlmacen = new CancelacionAlmacen()

        // TODO: populate valid domain properties
        assert cancelacionAlmacen.save() != null
        assert CancelacionAlmacen.count() == 1

        params.id = cancelacionAlmacen.id

        controller.delete()

        assert CancelacionAlmacen.count() == 0
        assert CancelacionAlmacen.get(cancelacionAlmacen.id) == null
        assert response.redirectedUrl == '/cancelacionAlmacen/list'


    }


}